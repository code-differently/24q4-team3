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

export default clientPromise;
