const apiKey: string = "5264d842";

document.addEventListener("DOMContentLoaded", () => {
    const searchInput = document.getElementById("movie-search") as HTMLInputElement;
    const searchResults = document.getElementById("search-results") as HTMLElement;
    const movieDetails = document.getElementById("movie-details") as HTMLElement;
    const movieInfo = document.getElementById("movie-info") as HTMLElement;
    const reviewList = document.getElementById("review-list") as HTMLElement;

    async function searchMovie(): Promise<void> {
        const query: string = searchInput.value.trim();
        if (!query) return;

        const apiUrl = `http://www.omdbapi.com/?s=${encodeURIComponent(query)}&apikey=${apiKey}`;
        
        try {
            const response = await fetch(apiUrl);
            const data = await response.json();

            if (data.Response === "True") {
                searchResults.innerHTML = data.Search.map((movie: any) => `
                    <div class="movie-item" onclick="getMovieDetails('${movie.imdbID}')">
                        <img src="${movie.Poster !== "N/A" ? movie.Poster : "placeholder.jpg"}" alt="${movie.Title}">
                        <h3>${movie.Title} (${movie.Year})</h3>
                    </div>
                `).join("");
            } else {
                searchResults.innerHTML = `<p>No results found for "${query}"</p>`;
            }
        } catch (error) {
            searchResults.innerHTML = "<p>Error fetching data. Please try again.</p>";
        }
    }

    async function getMovieDetails(imdbID: string): Promise<void> {
        const apiUrl = `http://www.omdbapi.com/?i=${imdbID}&apikey=${apiKey}`;

        try {
            const response = await fetch(apiUrl);
            const movie = await response.json();

            if (movie.Response === "True") {
                movieDetails.style.display = "block";
                movieInfo.innerHTML = `
                    <div class="movie-info">
                        <img src="${movie.Poster !== "N/A" ? movie.Poster : "placeholder.jpg"}" alt="${movie.Title}">
                        <h2>${movie.Title} (${movie.Year})</h2>
                        <p><strong>Genre:</strong> ${movie.Genre}</p>
                        <p><strong>Plot:</strong> ${movie.Plot}</p>
                        <p><strong>IMDb Rating:</strong> ${movie.imdbRating}</p>
                        <h3>User Reviews</h3>
                        <div id="review-list"></div>
                        <form id="review-form" onsubmit="addReview(event, '${movie.Title}')">
                            <textarea id="review-text" placeholder="Write your review..." required></textarea>
                            <button type="submit">Submit Review</button>
                        </form>
                    </div>
                `;
                movieDetails.scrollIntoView({ behavior: "smooth", block: "start" });
            } else {
                movieInfo.innerHTML = "<p>Movie details not found.</p>";
            }
        } catch (error) {
            movieInfo.innerHTML = "<p>Error fetching movie details.</p>";
        }
    }

    function addReview(event: Event, movieTitle: string): void {
        event.preventDefault();
        const reviewText = (document.getElementById("review-text") as HTMLTextAreaElement).value.trim();
        if (!reviewText) return;

        const reviewItem = document.createElement("div");
        reviewItem.classList.add("review-item");
        reviewItem.innerHTML = `<strong>${movieTitle}</strong>: ${reviewText}`;
        reviewList.prepend(reviewItem);

        (document.getElementById("review-text") as HTMLTextAreaElement).value = "";
    }

    // Assign functions to window for global access
    (window as any).searchMovie = searchMovie;
    (window as any).getMovieDetails = getMovieDetails;
    (window as any).addReview = addReview;
});
