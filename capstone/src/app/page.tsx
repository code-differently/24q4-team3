"use client";

import Link from "next/link";
import ChristmasScene from '../components/ChristmasScene';
import WishlistForm from '../components/WishlistForm';
import Snowfall from '../components/Snowfall';
import Image from 'next/image'; 

export default function Home() {
  return (
    <div className="flex flex-col md:flex-row items-center justify-center min-h-screen bg-nutmeg p-4 gap-8">
      <Snowfall /> 
      <WishlistForm />
      <ChristmasScene />
      
      {/* Send Invitation Button */}
      <Link href="/send-invitation">
        <button className="mt-6 p-3 bg-yellow-500 text-white rounded hover:bg-blue-700">
          Send Invitation
        </button>
      </Link>
    </div>
  );
}
