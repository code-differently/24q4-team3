// pages/api/send-invitation.ts
import type { NextApiRequest, NextApiResponse } from 'next';
import nodemailer from 'nodemailer';

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse
) {
  if (req.method !== 'POST') {
    return res.status(405).json({ message: 'Method Not Allowed' });
  }

  const { recipientName, recipientContact } = req.body;

  // Validate the input
  if (!recipientName || !recipientContact) {
    return res.status(400).json({ message: 'Missing required fields' });
  }

  try {
    
    const transporter = nodemailer.createTransport({
      service: 'gmail',  
      auth: {
        user: process.env.EMAIL_USER,  
        pass: process.env.EMAIL_PASS,  
      },
    });

    const mailOptions = {
      from: `"Wishes Under the Tree" <${process.env.EMAIL_USER}>`,
      to: recipientContact,
      subject: `You're Invited to View ${recipientName}'s Wishlist!`,
      html: `
        <p>Hi ${recipientName},</p>
        <p>${recipientName} has invited you to view their wishlist on <b>Wishes Under the Tree</b>.</p>
        <p>Click the button below to view it:</p>
        <a href="https://your-app.com/wishlist" style="background-color: #4CAF50; color: white; padding: 10px 20px; text-decoration: none;">View Wishlist</a>
        <p>This link will expire in 1 week.</p>
      `,
    };

    // Send the email
    await transporter.sendMail(mailOptions);

    // Send success response
    return res.status(200).json({ message: 'Invitation sent successfully' });
  } catch (error) {
    console.error('Error sending email:', error);
    return res.status(500).json({ message: 'Failed to send invitation' });
  }
}
