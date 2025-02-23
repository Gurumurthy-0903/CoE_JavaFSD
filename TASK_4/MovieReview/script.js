var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g = Object.create((typeof Iterator === "function" ? Iterator : Object).prototype);
    return g.next = verb(0), g["throw"] = verb(1), g["return"] = verb(2), typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (g && (g = 0, op[0] && (_ = 0)), _) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
var apiKey = "5264d842";
document.addEventListener("DOMContentLoaded", function () {
    var searchInput = document.getElementById("movie-search");
    var searchResults = document.getElementById("search-results");
    var movieDetails = document.getElementById("movie-details");
    var movieInfo = document.getElementById("movie-info");
    var reviewList = document.getElementById("review-list");
    function searchMovie() {
        return __awaiter(this, void 0, void 0, function () {
            var query, apiUrl, response, data, error_1;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        query = searchInput.value.trim();
                        if (!query)
                            return [2 /*return*/];
                        apiUrl = "http://www.omdbapi.com/?s=".concat(encodeURIComponent(query), "&apikey=").concat(apiKey);
                        _a.label = 1;
                    case 1:
                        _a.trys.push([1, 4, , 5]);
                        return [4 /*yield*/, fetch(apiUrl)];
                    case 2:
                        response = _a.sent();
                        return [4 /*yield*/, response.json()];
                    case 3:
                        data = _a.sent();
                        if (data.Response === "True") {
                            searchResults.innerHTML = data.Search.map(function (movie) { return "\n                    <div class=\"movie-item\" onclick=\"getMovieDetails('".concat(movie.imdbID, "')\">\n                        <img src=\"").concat(movie.Poster !== "N/A" ? movie.Poster : "placeholder.jpg", "\" alt=\"").concat(movie.Title, "\">\n                        <h3>").concat(movie.Title, " (").concat(movie.Year, ")</h3>\n                    </div>\n                "); }).join("");
                        }
                        else {
                            searchResults.innerHTML = "<p>No results found for \"".concat(query, "\"</p>");
                        }
                        return [3 /*break*/, 5];
                    case 4:
                        error_1 = _a.sent();
                        searchResults.innerHTML = "<p>Error fetching data. Please try again.</p>";
                        return [3 /*break*/, 5];
                    case 5: return [2 /*return*/];
                }
            });
        });
    }
    function getMovieDetails(imdbID) {
        return __awaiter(this, void 0, void 0, function () {
            var apiUrl, response, movie, error_2;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        apiUrl = "http://www.omdbapi.com/?i=".concat(imdbID, "&apikey=").concat(apiKey);
                        _a.label = 1;
                    case 1:
                        _a.trys.push([1, 4, , 5]);
                        return [4 /*yield*/, fetch(apiUrl)];
                    case 2:
                        response = _a.sent();
                        return [4 /*yield*/, response.json()];
                    case 3:
                        movie = _a.sent();
                        if (movie.Response === "True") {
                            movieDetails.style.display = "block";
                            movieInfo.innerHTML = "\n                    <div class=\"movie-info\">\n                        <img src=\"".concat(movie.Poster !== "N/A" ? movie.Poster : "placeholder.jpg", "\" alt=\"").concat(movie.Title, "\">\n                        <h2>").concat(movie.Title, " (").concat(movie.Year, ")</h2>\n                        <p><strong>Genre:</strong> ").concat(movie.Genre, "</p>\n                        <p><strong>Plot:</strong> ").concat(movie.Plot, "</p>\n                        <p><strong>IMDb Rating:</strong> ").concat(movie.imdbRating, "</p>\n                        <h3>User Reviews</h3>\n                        <div id=\"review-list\"></div>\n                        <form id=\"review-form\" onsubmit=\"addReview(event, '").concat(movie.Title, "')\">\n                            <textarea id=\"review-text\" placeholder=\"Write your review...\" required></textarea>\n                            <button type=\"submit\">Submit Review</button>\n                        </form>\n                    </div>\n                ");
                            movieDetails.scrollIntoView({ behavior: "smooth", block: "start" });
                        }
                        else {
                            movieInfo.innerHTML = "<p>Movie details not found.</p>";
                        }
                        return [3 /*break*/, 5];
                    case 4:
                        error_2 = _a.sent();
                        movieInfo.innerHTML = "<p>Error fetching movie details.</p>";
                        return [3 /*break*/, 5];
                    case 5: return [2 /*return*/];
                }
            });
        });
    }
    function addReview(event, movieTitle) {
        event.preventDefault();
        var reviewText = document.getElementById("review-text").value.trim();
        if (!reviewText)
            return;
        var reviewItem = document.createElement("div");
        reviewItem.classList.add("review-item");
        reviewItem.innerHTML = "<strong>".concat(movieTitle, "</strong>: ").concat(reviewText);
        reviewList.prepend(reviewItem);
        document.getElementById("review-text").value = "";
    }
    // Assign functions to window for global access
    window.searchMovie = searchMovie;
    window.getMovieDetails = getMovieDetails;
    window.addReview = addReview;
});
