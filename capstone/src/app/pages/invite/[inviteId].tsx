import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import React from 'react';

interface InviteData {
  senderName: string;
  recipientName: string;
  wishlist: string[];
}

const InvitePage = () => {
  const router = useRouter();
  const { inviteId } = router.query; 
  const [inviteData, setInviteData] = useState<InviteData | null>(null); 

  useEffect(() => {
    if (inviteId) {
      
      fetch(`/api/invite/${inviteId}`)
        .then((res) => res.json())
        .then((data: InviteData) => setInviteData(data)) 
        .catch((error) => console.error('Error fetching invite data:', error));
    }
  }, [inviteId]);

  return (
    <div>
      <h1>Invite Page</h1>
      {inviteData ? (
        <div>
          <h2>{inviteData.senderName}&amp;#39s Wishlist</h2>
          <p>{inviteData.recipientName}, you have read-only access to this wishlist.</p>
          {/* Render wishlist details here */}
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
};

export default InvitePage;
