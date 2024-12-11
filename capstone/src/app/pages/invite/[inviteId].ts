// pages/api/invite/[inviteId].ts
import { NextApiRequest, NextApiResponse } from 'next';
import { getInviteFromDatabase } from '../../lib/mongodb'; 
import { saveInviteToDatabase } from '../../../app/lib/inviteStorage';

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
  const { inviteId } = req.query;

  try {
    const invite = await getInviteFromDatabase(inviteId as string);
    
    if (invite) {
      res.status(200).json(invite);
    } else {
      res.status(404).json({ message: 'Invite not found' });
    }
  } catch (error) {
    console.error('Database Error:', error);
    res.status(500).json({ message: 'Internal server error' });
  }
}
