
import ChristmasScene from './components/ChristmasScene';
import WishlistForm from './components/WishlistForm';

export default function Home() {
  return (
    <div className="flex items-center justify-center min-h-screen bg-nutmeg p-4">
      <WishlistForm />
      <ChristmasScene/>
    </div>
  );
}

