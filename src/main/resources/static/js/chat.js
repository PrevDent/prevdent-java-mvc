document.addEventListener('DOMContentLoaded', function() {
    window.setQuestion = function(question) {
        document.querySelector('input[type="text"]').value = question;
    };

    const chatMessages = document.getElementById('chatMessages');
    const loadingIndicator = document.getElementById('loadingIndicator');
    const chatForm = document.querySelector('.chat-input');
    const inputField = chatForm.querySelector('input[type="text"]');
    const sendButton = chatForm.querySelector('.btn-send');

    chatMessages.scrollTop = chatMessages.scrollHeight;

    chatForm.addEventListener('submit', function(e) {
        loadingIndicator.style.display = 'block';
        chatForm.classList.add('form-loading');
        sendButton.disabled = true;

        setTimeout(() => {
            chatMessages.scrollTop = chatMessages.scrollHeight;
        }, 100);

        setTimeout(() => {
            loadingIndicator.style.display = 'none';
            chatForm.classList.remove('form-loading');
            sendButton.disabled = false;

            setTimeout(() => {
                chatMessages.scrollTop = chatMessages.scrollHeight;
            }, 100);
        }, 1500);
    });

    inputField.focus();

    inputField.addEventListener('keypress', function(e) {
        if (e.key === 'Enter' && !e.shiftKey) {
            e.preventDefault();
            chatForm.dispatchEvent(new Event('submit'));
        }
    });
});