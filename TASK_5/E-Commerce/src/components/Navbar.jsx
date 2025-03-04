import { useCart } from "../context/CartContext";

const Navbar = () => {
    const { cart } = useCart();

    return (
        <nav className="bg-gray-900 text-white py-4">
            <div className="container mx-auto flex justify-between items-center">
                <h1 className="text-2xl font-bold">E-Commerce</h1>
                <div className="space-x-4">
                    <a href="/" className="hover:text-gray-300">Home</a>
                    <a href="/cart" className="hover:text-gray-300">
                        Cart ({cart.length})
                    </a>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
