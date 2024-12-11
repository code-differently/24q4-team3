// pages/api/send-invitation.ts
import type { NextApiRequest, NextApiResponse } from 'next';
import nodemailer from 'nodemailer';
import ejs from 'ejs';
import fs from 'fs';

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

    const templateString = fs.readFileSync('./email-template.ejs', 'utf-8');

    const data = {
      recipientName: 'family',
    };

    const html = ejs.render(templateString, data);

    const mailOptions = {
      from: `"Wishes Under the Tree" <${process.env.EMAIL_USER}>`,
      to: recipientContact,
      subject: `You're Invited to View ${recipientName}'s Wishlist!`,
      html: html,
    };

    // Send the email
    const info = await transporter.sendMail(mailOptions);
    console.log(`Email sent: ${info.messageId}`);

    
    // Send success response
    return res.status(200).json({ message: 'Invitation sent successfully' });
  } catch (error) {
    console.error('Error sending email:', error);
    return res.status(500).json({ message: 'Failed to send invitation' });
  }
}
