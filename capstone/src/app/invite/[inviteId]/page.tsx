'use client';

import { useEffect, useState } from 'react';
import React from 'react';

type InvitePageProps = {
  params: Promise<{ inviteId: string }>;
};

interface InviteData {
  senderName: string;
  recipientName: string;
  wishlist: string[];
}

export default function InvitePage({params}: InvitePageProps) {
  const [inviteData, setInviteData] = useState<InviteData | null>(null); 

  useEffect(() => {
    const fetchInviteData = async () => {
      try {
        const { inviteId } = await params;
        const res = await fetch(`/api/invite/${inviteId}`);
        const data = await res.json();
        setInviteData(data);
      } catch (error) {
        console.error('Error fetching invite data:', error);
      };
    };
    fetchInviteData();
  }, []);

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
