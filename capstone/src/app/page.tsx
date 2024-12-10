"use client";

import Link from "next/link";
import ChristmasScene from './components/ChristmasScene';
import WishlistForm from './components/WishlistForm';
import Snowfall from './components/Snowfall';
import Image from 'next/image'; 

export default function Home() {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-nutmeg p-4">
      {/* Logo */}
      <div className="mb-4">
        <Image 
          src="/logo.png"  
          alt="App Logo"    
          width={150}       
          height={150}      
        />
      </div>

      {/* Main Content */}
      <Snowfall />
      <h1 className="text-3xl font-bold mb-6 text-white text-center">
        Welcome to Wishes Under the Tree
      </h1>
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
