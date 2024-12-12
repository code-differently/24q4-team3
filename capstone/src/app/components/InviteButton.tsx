import Link from 'next/link';

const InviteButton = () => {
  return (
    <div className="flex items-center justify-center">
    <Link href="/send-invitation">
      <button
        type="button"
        className="w-full px-4 py-2 text-white font-semibold rounded-md shadow-md bg-transparent border-2 border-white hover:bg-white hover:text-nutmeg transition duration-300 ease-in-out"
      >
        Share List 
      </button>
    </Link>
    </div>
  );
};

export default InviteButton;
 