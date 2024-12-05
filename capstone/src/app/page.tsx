
import WishlistForm from './components/WishlistForm';
import ChristmasScene from "./components/ChristmasScene";

export default function Home() {
  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100 p-4">
      <WishlistForm />
      <ChristmasScene/>
      
    </div>
  );
}

