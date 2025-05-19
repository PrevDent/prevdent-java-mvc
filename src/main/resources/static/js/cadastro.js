function formatarCPF(campo) {
    let cpf = campo.value.replace(/\D/g, "");

    cpf = cpf.substring(0, 11);

    cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
    cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
    cpf = cpf.replace(/(\d{3})(\d{1,2})$/, "$1-$2");

    campo.value = cpf;


    campo.setSelectionRange(cpf.length, cpf.length);
}

document.getElementById('cpf').addEventListener('input', function() {
    formatarCPF(this);
});