import { useCart } from "../context/CartContext";
import { useNavigate } from "react-router-dom";

const CheckoutPage = () => 
{
    const { cart, totalPrice, clearCart } = useCart();
    const navigate = useNavigate();

    const handlePlaceOrder = () => 
    {
        alert("Order placed successfully! 🎉");
        clearCart();
        navigate("/");
    };

    return (
        <div className="p-6">
            <h2 className="text-2xl font-bold mb-4">Checkout</h2>
            <div className="space-y-4">
                {cart.map((item) => (
                    <div key={item.id} className="flex justify-between border p-4 rounded">
                        <h3 className="text-lg">{item.name} (x{item.quantity})</h3>
                        <p>₹{item.price * item.quantity}</p>
                    </div>
                ))}
                <div className="mt-6 p-4 border-t">
                    <h3 className="text-xl font-semibold">Total: ₹{totalPrice}</h3>
                </div>
                <button 
                    onClick={handlePlaceOrder} 
                    className="mt-4 px-6 py-2 bg-green-500 text-white rounded"
                >
                    Place Order
                </button>
            </div>
        </div>
    );
};

export default CheckoutPage;
