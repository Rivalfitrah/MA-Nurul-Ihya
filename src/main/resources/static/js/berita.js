function toggleNews() {
    var summary = document.querySelector('.news-summary');
    var full = document.querySelector('.news-full');
    if (full.style.display === "none") {
        full.style.display = "block";
        summary.style.display = "none";
    } else {
        full.style.display = "none";
        summary.style.display = "block";
    }
}
// ==================================================================
    // berita selengkapnya

    function redirectBerita(title) {
        window.location.href = 'berita?title=' + title;
    }
    
    function getParameterByName(name, url = window.location.href) {
        name = name.replace(/[\[\]]/g, '\\$&');
        let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));      
    }

    // Menampilkan konten berita sesuai dengan parameter 'judul'
        function loadBerita() {
            const title = getParameterByName('title');
            let konten = '';

            if (title === 'berita') {
                konten = `
                    <h1>Judul Berita </h1>
                    <p>Ini adalah isi lengkap dari berita .</p>
                `;
            } else if (title === 'berita') {
                konten = `
                    <h1>Judul Berita </h1>
                    <p>Ini adalah isi lengkap dari berita .</p>
                `;
            } else {
                konten = `<p>Berita tidak ditemukan.</p>`;
            }

            document.getElementById('konten-berita').innerHTML = konten;
        }

    window.onload = loadBerita;