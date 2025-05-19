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

function validarCPF(cpf) {
    cpf = cpf.replace(/\D/g, '');

    if (cpf.length !== 11) return false;

    if (/^(\d)\1{10}$/.test(cpf)) return false;

    let soma = 0;
    for (let i = 0; i < 9; i++) {
        soma += parseInt(cpf.charAt(i)) * (10 - i);
    }
    let resto = 11 - (soma % 11);
    let digitoVerificador1 = resto > 9 ? 0 : resto;

    if (digitoVerificador1 !== parseInt(cpf.charAt(9))) return false;

    soma = 0;
    for (let i = 0; i < 10; i++) {
        soma += parseInt(cpf.charAt(i)) * (11 - i);
    }
    resto = 11 - (soma % 11);
    let digitoVerificador2 = resto > 9 ? 0 : resto;

    if (digitoVerificador2 !== parseInt(cpf.charAt(10))) return false;

    return true;
}

document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    const senhaInput = document.getElementById('senha');
    const togglePassword = document.querySelector('.toggle-password');
    const cpfInput = document.getElementById('cpf');
    const cpfError = document.getElementById('cpf-error');

    if (togglePassword) {
        togglePassword.addEventListener('click', function() {
            const type = senhaInput.getAttribute('type') === 'password' ? 'text' : 'password';
            senhaInput.setAttribute('type', type);
            const icon = this.querySelector('i');
            icon.classList.toggle('fa-eye');
            icon.classList.toggle('fa-eye-slash');
            this.setAttribute('aria-label', type === 'password' ? 'Mostrar senha' : 'Ocultar senha');
        });
    }

    if (cpfInput) {
        cpfInput.addEventListener('input', function() {
            formatarCPF(this);

            this.classList.remove('input-error');
            if (cpfError) cpfError.style.display = 'none';
        });

        cpfInput.addEventListener('blur', function() {
            if (this.value && !validarCPF(this.value)) {
                this.classList.add('input-error');
                if (cpfError) {
                    cpfError.textContent = 'CPF inválido';
                    cpfError.style.display = 'block';
                }
            } else {
                this.classList.remove('input-error');
                if (cpfError) cpfError.style.display = 'none';
            }
        });
    }

    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            if (cpfInput && !validarCPF(cpfInput.value)) {
                e.preventDefault();
                cpfInput.classList.add('input-error');
                if (cpfError) {
                    cpfError.textContent = 'Por favor, insira um CPF válido';
                    cpfError.style.display = 'block';
                }
                cpfInput.focus();
                return;
            }

            const submitButton = this.querySelector('button[type="submit"]');
            const buttonText = submitButton.querySelector('.button-text');
            const buttonIcon = submitButton.querySelector('i');

            submitButton.disabled = true;
            buttonText.textContent = 'Autenticando...';
            buttonIcon.className = 'fas fa-spinner fa-spin';


            setTimeout(() => {
                submitButton.style.background = 'linear-gradient(135deg, var(--success-color), #05b38d)';
                buttonText.textContent = 'Acesso concedido!';
                buttonIcon.className = 'fas fa-check';

                setTimeout(() => {
                    window.location.href = '/dashboard';
                }, 1000);
            }, 1500);
        });
    }

    document.querySelectorAll('input').forEach(input => {
        input.addEventListener('focus', function() {
            this.parentElement.querySelector('i').style.color = 'var(--primary-color)';
        });

        input.addEventListener('blur', function() {
            this.parentElement.querySelector('i').style.color = 'var(--text-light)';
        });
    });
});