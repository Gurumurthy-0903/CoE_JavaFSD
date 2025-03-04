import { useCart } from "../context/CartContext";

const ProductCard = ({ product }) => {
    const { addToCart } = useCart();

    return (
        <div className="border p-4 rounded-lg shadow-lg">
            <img src={product.image} alt={product.name} className="w-full h-40 object-contain" />
            <h2 className="text-lg font-bold mt-2">{product.name}</h2>
            <p className="text-gray-600">₹{product.price}</p>
            <button
                onClick={() => addToCart(product)}
                className="bg-blue-500 text-white px-4 py-2 mt-2 rounded-lg hover:bg-blue-600"
            >
                Add to Cart
            </button>
        </div>
    );
};

export default ProductCard;