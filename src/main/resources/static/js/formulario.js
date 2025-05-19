document.addEventListener('DOMContentLoaded', function() {
    function formatarCPF(campo) {
        let cpf = campo.value.replace(/\D/g, '');
        cpf = cpf.substring(0, 11);

        cpf = cpf.replace(/(\d{3})(\d{0,3})(\d{0,3})(\d{0,2})/, (match, p1, p2, p3, p4) => {
            let formatted = p1;
            if (p2) formatted += `.${p2}`;
            if (p3) formatted += `.${p3}`;
            if (p4) formatted += `-${p4}`;
            return formatted;
        });

        campo.value = cpf;
    }

    const cpfInput = document.getElementById('cpfPaciente');
    if (cpfInput) {
        cpfInput.addEventListener('input', function() {
            formatarCPF(this);
        });
    }

    const form = document.querySelector('form');
    if (form) {
        form.addEventListener('submit', function(e) {
            const submitButton = this.querySelector('button[type="submit"]');
            const buttonText = submitButton.querySelector('span');
            const buttonIcon = submitButton.querySelector('i');

            submitButton.disabled = true;
            buttonText.textContent = 'Agendando...';
            buttonIcon.className = 'fas fa-spinner fa-spin';

            setTimeout(() => {
                submitButton.style.background = 'linear-gradient(135deg, var(--success-color), #05b38d)';
                buttonText.textContent = 'Consulta Agendada!';
                buttonIcon.className = 'fas fa-check';

                setTimeout(() => {
                    window.location.href = '/usuario/consultas';
                }, 1000);
            }, 1500);
        });
    }
});