document.addEventListener('DOMContentLoaded', () => {
    const navigationItems = document.querySelectorAll('.page, .nextpostslink');
    const content = document.getElementById('content');
    let currentPage = 1;

    const loadPageContent = (page) => {
        fetch(`pages/page${page}.html`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Page not found');
                }
                return response.text();
            })
            .then(data => {
                content.innerHTML = data;
                currentPage = page;
                updateNavigation(page);
            })
            .catch(error => {
                content.innerHTML = '<p>Halaman tidak ditemukan.</p>';
            });
    };

    const updateNavigation = (page) => {
        navigationItems.forEach(item => {
            if (item.classList.contains('page')) {
                item.classList.remove('current');
                if (parseInt(item.getAttribute('data-page')) === page) {
                    item.classList.add('current');
                }
            }
        });
    };

    navigationItems.forEach(item => {
        item.addEventListener('click', () => {
            let page;
            if (item.getAttribute('data-next')) {
                page = currentPage + 1;
            } else {
                page = parseInt(item.getAttribute('data-page'));
            }
            loadPageContent(page);
        });
    });

    // Load initial page content
    loadPageContent(currentPage);
});
