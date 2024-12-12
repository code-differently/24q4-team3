import { getInviteFromDatabase } from '@/app/lib/mongodb';

export async function GET(req: Request, {params}: {params: Promise<{ inviteId: string }>}) {
  const { inviteId } = await params;

  try {
    const invite = await getInviteFromDatabase(inviteId);
    
    if (invite) {
      return Response.json(invite);
    } else {
      return Response.json({ message: 'Invite not found' }, { status: 404 });
    }
  } catch (error) {
    console.error('Database Error:', error);
    return Response.json({ message: 'Internal server error' }, { status: 500 });
  }
}
