import { useCart } from "../context/CartContext";
import { useNavigate } from "react-router-dom";

const CartPage = () => 
{
    const { cart, addToCart, removeFromCart, decreaseQuantity, clearCart, totalPrice } = useCart();
    const navigate = useNavigate();

    return (
        <div className="p-6">
            <h2 className="text-2xl font-bold mb-4">Shopping Cart</h2>
            {cart.length === 0 ? (
                <p>Your cart is empty.</p>
            ) : (
                <div className="space-y-4">
                    {cart.map((item) => (
                        <div key={item.id} className="flex justify-between items-center border p-4 rounded">
                            <div className="flex items-center space-x-4">
                                <img src={item.image} alt={item.name} className="w-20 h-20 object-cover" />
                                <div>
                                    <h3 className="text-lg font-semibold">{item.name}</h3>
                                    <p>₹{item.price}</p>
                                    <p>Quantity: {item.quantity}</p>
                                </div>
                            </div>
                            <div className="space-x-2">
                                <button onClick={() => decreaseQuantity(item.id)} className="px-3 py-1 bg-gray-300 rounded">-</button>
                                <button onClick={() => addToCart(item)} className="px-3 py-1 bg-gray-300 rounded">+</button>
                                <button onClick={() => removeFromCart(item.id)} className="px-4 py-1 bg-red-500 text-white rounded">Remove</button>
                            </div>
                        </div>
                    ))}
                    <div className="mt-6 p-4 border-t">
                        <h3 className="text-xl font-semibold">Total: ₹{totalPrice}</h3>
                    </div>
                    <button onClick={clearCart} className="mt-4 px-6 py-2 bg-red-500 text-white rounded">Clear Cart</button>
                    
                    {/* Checkout Button */}
                    <button 
                        onClick={() => navigate("/checkout")} 
                        className="mt-4 px-6 py-2 bg-blue-500 text-white rounded"
                    >
                        Proceed to Checkout
                    </button>
                </div>
            )}
        </div>
    );
};

export default CartPage;
