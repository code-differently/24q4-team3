

import { NextApiRequest, NextApiResponse } from 'next';
import nodemailer from 'nodemailer';

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
  if (req.method !== 'POST') {
    return res.status(405).json({ error: 'Method not allowed' });
  }

  const { recipientName, recipientContact, isEmail } = req.body;

  if (!recipientName || !recipientContact) {
    return res.status(400).json({ error: 'Recipient name and contact are required.' });
  }

  try {
    if (isEmail) {
      // Nodemailer transport setup
      const transporter = nodemailer.createTransport({
        service: 'Gmail', // Or your email service
        auth: {
          user: process.env.EMAIL_USER, 
          pass: process.env.EMAIL_PASSWORD, 
        },
      });

      // Send email
      await transporter.sendMail({
        from: `"Wishes Under the Tree" <${process.env.EMAIL_USER}>`, 
        to: recipientContact, 
        subject: `You're Invited to View ${recipientName}'s Wishlist!`,
        html: `<p>Hi ${recipientName},</p>
               <p>${recipientName} has invited you to view their wishlist on <b>Wishes Under the Tree</b>.</p>
               <p>Click the button below to view it:</p>
               <a href="https://your-app.com/wishlist" style="background-color: #4CAF50; color: white; padding: 10px 20px; text-decoration: none;">View Wishlist</a>
               <p>This link will expire in 1 week.</p>`,
      });

      return res.status(200).json({ message: 'Invitation email sent successfully.' });
    }

    res.status(400).json({ error: 'Email sending is not configured yet.' });
  } catch (error) {
    console.error('Error sending email:', error);
    return res.status(500).json({ error: 'Failed to send invitation.' });
  }
}
