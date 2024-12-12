
import ChristmasScene from '../components/ChristmasScene';
import WishlistForm from '../components/WishlistForm';
import Snowfall from '../components/Snowfall';

export default function Home() {
  return (
    <div className="flex flex-col md:flex-row items-center justify-center min-h-screen bg-nutmeg p-4 gap-8">
      <Snowfall /> 
      <WishlistForm />
      <ChristmasScene/>
    </div>
  );
}

