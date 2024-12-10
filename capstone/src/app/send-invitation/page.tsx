import { useState } from 'react';

const SendInvitation = () => {
  const [recipientName, setRecipientName] = useState('');
  const [recipientContact, setRecipientContact] = useState('');
  const [isEmail, setIsEmail] = useState(true);
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    try {
      const response = await fetch('/api/send-invitation', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          recipientName,
          recipientContact,
          isEmail,
        }),
      });

      if (!response.ok) throw new Error('Failed to send invitation');

      alert('Invitation sent successfully!');
      setRecipientName('');
      setRecipientContact('');
    } catch (error) {
      // Type Guard: check if 'error' is an instance of Error
      if (error instanceof Error) {
        alert(error.message); // Access the message safely
      } else {
        // If the error is not an instance of Error, handle it generically
        alert('An unexpected error occurred.');
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h1>Send an Invitation</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Recipient Name:
          <input
            type="text"
            value={recipientName}
            onChange={(e) => setRecipientName(e.target.value)}
            required
          />
        </label>
        <label>
          Contact (Email/Phone):
          <input
            type="text"
            value={recipientContact}
            onChange={(e) => setRecipientContact(e.target.value)}
            required
          />
        </label>
        <label>
          Send via:
          <select onChange={(e) => setIsEmail(e.target.value === 'email')}>
            <option value="email">Email</option>
            <option value="sms">SMS</option>
          </select>
        </label>
        <button type="submit" disabled={loading}>
          {loading ? 'Sending...' : 'Send Invitation'}
        </button>
      </form>
    </div>
  );
};

export default SendInvitation;
