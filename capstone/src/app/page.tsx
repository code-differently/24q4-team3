
import ChristmasScene from './components/ChristmasScene';
import WishlistForm from './components/WishlistForm';
import Snowfall from './components/Snowfall';

export default function Home() {
  return (
    <div className="flex items-center justify-center min-h-screen bg-nutmeg p-4">
      <Snowfall /> 
      <WishlistForm />
      <ChristmasScene/>
    </div>
  );
}

