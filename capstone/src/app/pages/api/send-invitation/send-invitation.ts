import type { NextApiRequest, NextApiResponse } from 'next';
import nodemailer from 'nodemailer';
import crypto from 'crypto'; 
import { getInviteFromDatabase } from '../../lib/mongodb';
import { saveInviteToDatabase } from '../../../lib/inviteStorage'; 

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
  if (req.method !== 'POST') {
    return res.status(405).json({ message: 'Method Not Allowed' });
  }

  const { recipientEmail, recipientName, senderName } = req.body;

  if (!recipientEmail || !recipientName || !senderName) {
    return res.status(400).json({ message: 'Missing required fields' });
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
      service: 'gmail',
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
    res.status(200).json({ message: 'Invitation sent successfully' });
  } catch (error) {
    console.error('Error sending email:', error);
    res.status(500).json({ message: 'Failed to send invitation' });
  }
}
