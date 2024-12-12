import type { NextApiRequest, NextApiResponse } from 'next';
import nodemailer from 'nodemailer';
import crypto from 'crypto'; 
import { saveInviteToDatabase } from '../../lib/mongodb';
import { currentUser } from '@clerk/nextjs/server';

// Exports an API route handler for POST requests.
export async function POST(req: Request) {
  // Get the current user's details.
  const user = await currentUser();
  const senderName = `${user?.firstName} ${user?.lastName}`;

  // Get the recipient's email and name from the request body.
  const { recipientEmail, recipientName } = await req.json();

  if (!recipientEmail || !recipientName) {
    return Response.json({ message: 'Missing required fields'}, { status: 400 });
  }

  try {
   
    const inviteId = crypto.randomBytes(16).toString('hex');

   
    await saveInviteToDatabase(inviteId, {
      senderName,
      recipientName,
      recipientEmail,
      permissions: 'read-only',
    });
    
 
    const transporter = nodemailer.createTransport({
      service: "Gmail",
      host: "smtp.gmail.com",
      port: 465,
      secure: true,
      auth: {
        user: process.env.GMAIL_USER,
        pass: process.env.GMAIL_APP_PASSWORD,
      },
    });

    const link = `${process.env.NEXT_PUBLIC_APP_URL}/invite/${inviteId}`;
    const mailOptions = {
      from: `"${senderName}" <${process.env.GMAIL_USER}>`,
      to: recipientEmail,
      subject: `You're Invited to View ${senderName}'s Wishlist!`,
      html: `
        <p>Hi ${recipientName},</p>
        <p>${senderName} has invited you to view their wishlist.</p>
        <p>Click <a href="${link}">here</a> to view it.</p>
      `,
    };

    await transporter.sendMail(mailOptions);
    return Response.json({ message: 'Invitation sent successfully'});
  } catch (error) {
    console.error('Error sending email:', error);
    return Response.json({ message: 'Failed to send invitation' }, { status: 500 });
  }
}
