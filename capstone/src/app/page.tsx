
import ChristmasScene from '../components/ChristmasScene';
import WishlistForm from '../components/WishlistForm';
import Snowfall from '../components/Snowfall';
import InviteButton from './components/InviteButton';

export default function Home() {
  return (
    <div className="flex flex-col md:flex-row items-center justify-center min-h-screen bg-nutmeg p-4 gap-8">
      <Snowfall /> 
      <WishlistForm />
      <div>
      <ChristmasScene/>
      <InviteButton/>
      </div>
    </div>
  );
}

