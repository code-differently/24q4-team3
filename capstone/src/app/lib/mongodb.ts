import { MongoClient, ServerApiVersion } from 'mongodb';


const uri = "mongodb+srv://ynabdullah244:group3@wishunderthetree.iktqe.mongodb.net/?retryWrites=true&w=majority";
const client = new MongoClient(uri, {
  serverApi: {
    version: ServerApiVersion.v1,
    strict: true,
    deprecationErrors: true,
  },
});

let clientPromise: Promise<MongoClient>;

if (process.env.NODE_ENV === 'development') {
  const globalClient = global as typeof globalThis & { _mongoClientPromise?: Promise<MongoClient> };
  if (!globalClient._mongoClientPromise) {
    globalClient._mongoClientPromise = client.connect();
  }
  clientPromise = globalClient._mongoClientPromise;
} else {
  clientPromise = client.connect();
}


const getDb = async () => {
  const client = await clientPromise;
  return client.db('yourDatabaseName'); 
};


interface InviteData {
  senderName: string;
  recipientName: string;
  recipientEmail: string;
  permissions: 'read-only' | 'read-write'; 
}


export const saveInviteToDatabase = async (inviteId: string, inviteData: InviteData) => {
  try {
    const db = await getDb();
    const invitesCollection = db.collection('invites'); 
    await invitesCollection.insertOne({
      inviteId,
      ...inviteData,
      createdAt: new Date(),
    });
    console.log(`Invite saved to database with inviteId: ${inviteId}`);
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to save invite');
  }
};


export const getInviteFromDatabase = async (inviteId: string): Promise<InviteData | null> => {
  try {
    const db = await getDb();
    const invitesCollection = db.collection('invites');
    const invite = await invitesCollection.findOne({ inviteId });
    
    return invite as InviteData | null; 
  } catch (error) {
    console.error('Database Error:', error);
    throw new Error('Failed to fetch invite');
  }
};

export default clientPromise;
