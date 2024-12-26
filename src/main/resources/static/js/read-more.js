// read-more.js

document.addEventListener("DOMContentLoaded", function() {
    const readMoreBtns = document.querySelectorAll(".read-more-btn");
    const modal = new bootstrap.Modal(document.getElementById('readMoreModal'));
    const modalContent = document.getElementById('modalContent');

    readMoreBtns.forEach(btn => {
        btn.addEventListener("click", function() {
            const content = this.getAttribute('data-content');
            modalContent.textContent = content;
            modal.show();
        });
    });
});
