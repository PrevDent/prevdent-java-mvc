document.addEventListener('DOMContentLoaded', function() {
    function formatarCPFs() {
        document.querySelectorAll('td:nth-child(2)').forEach(cell => {
            const cpf = cell.textContent.trim();
            if (cpf.length === 11) {
                cell.textContent = cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
            }
        });
    }

    formatarCPFs();

    const searchInput = document.querySelector('.search-box input');
    if (searchInput) {
        searchInput.addEventListener('input', function() {
            const searchTerm = this.value.toLowerCase();
            const rows = document.querySelectorAll('tbody tr');

            rows.forEach(row => {
                const rowText = row.textContent.toLowerCase();
                row.style.display = rowText.includes(searchTerm) ? '' : 'none';
            });
        });
    }


    const deleteButtons = document.querySelectorAll('.action-btn.delete');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            if (confirm('Tem certeza que deseja cancelar esta consulta?')) {
                this.closest('tr').style.opacity = '0.5';
                setTimeout(() => {
                    this.closest('tr').remove();
                }, 1000);
            }
        });
    });
});