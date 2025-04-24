document.addEventListener("DOMContentLoaded", function () {
    const chatIcon = document.getElementById('chat-icon');
    const chatWindow = document.getElementById('chat-window');
    const sendBtn = document.getElementById('send-btn');
    const inputField = document.getElementById('chat-input');
    const chatMessages = document.getElementById('chat-messages');

    // Toggle chatbot window
    chatIcon.addEventListener('click', () => {
        chatWindow.classList.toggle('hidden');
    });

    // Send query
    sendBtn.addEventListener('click', async () => {
        const input = inputField.value.trim();
        if (!input) return;

        addMessage("You: " + input);
        inputField.value = "";

        try {
            const res = await fetch('http://localhost:8080/api/prescription-bot/explain', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ query: input })
            });

            const text = await res.text();
            addMessage("Bot: " + text);
        } catch (err) {
            addMessage("Bot: Something went wrong 😔");
        }
    });

    function addMessage(text) {
        const msg = document.createElement('div');
        msg.textContent = text;
        chatMessages.appendChild(msg);
        chatMessages.scrollTop = chatMessages.scrollHeight;
    }
});
