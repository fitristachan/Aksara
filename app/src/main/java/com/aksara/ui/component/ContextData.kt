package com.aksara.ui.component

data class ContextData(
    val id: Int,
    val title: String,
    val context: String,
    val sample_question: List<String>
)

val contextItems = listOf(
    ContextData(
        id = 1,
        title = "Sejarah Aksara Jawa",
        context = "Cerita asal mula aksara Jawa bercerita tentang pemuda yang pemberani bernama Aji Saka. Aji Saka memiliki dua punggawa, Dhora dan Sembadha, serta ada prabu bernama Prabu Dewata Cengkar. Prabu Dewata Cengkar adalah seorang prabu yang suka makan daging manusia. Aji Saka menantang Prabu Dewata Cengkar untuk bertarung dengan syarat Dewata Cengkar harus memberikan tanah seluas panjang surban Aji Saka. Prabu Dewata Cengkar pun setuju, tapi karena kekuatan Aji Saka, kain surban Aji Saka memanjang hingga ke laut Pantai Selatan. Lalu Aji Saka menghempaskan Dewata Cengkar ke laut hingga berubah menjadi buaya putih. Sehingga, hasil dari pertarungan antara Aji Saka dan Prabu Dewata Cengkar adalah Prabu Dewata Cengkar kalah. Aji Saka menjadi raja di Negara Madhangkamulan. Ia mengutus Dhora untuk mengambil keris pusaka dari Sembadha di Pulau Majethi. Sembadha tidak mengizinkan selain Aji Saka mengambil keris, sehingga Sembadha dan Dhora bertarung dan keduanya tewas. Aji Saka menciptakan aksara Jawa, yaitu hanacaraka, datasawala, padhajayanya, dan magabathanga karena menyesal atas kematian punggawanya. Hanacaraka artinya ada utusan. Datasawala artinya saling berselisih pendapat. Padhajayanya artinya semuanya sama sakti. Magabathanga artinya semuanya menjadi mayat.",
        sample_question = listOf(
            "Siapa itu Aji Saka?",
            "Apa hasil pertarungan antara Aji Saka dan Prabu Dewata Cengkar?",
            "Apa arti hanacaraka?"
        )
    ),
    ContextData(
        id = 2,
        title = "Wayang Jawa",
        context = "Wayang adalah seni pertunjukan tradisional asli Indonesia yang berasal atau berkembang pesat di Jawa dan Bali. UNESCO menetapkan wayang sebagai Warisan Mahakarya Dunia Tak Ternilai dalam Seni Bertutur. Wayang pertama kali tercatat dalam Prasasti Balitung pada abad ke-10. Wayang terdiri dari beberapa jenis, yaitu wayang kulit yang terbuat dari kulit sapi, wayang wong yang dimainkan oleh manusia, dan wayang golek yang terbuat dari kayu. Wayang sering menceritakan kisah-kisah dari Ramayana dan Mahabharata, serta digunakan oleh para Wali Songo untuk menyebarkan ajaran Islam. Wayang masih populer hingga sekarang berkat inovasi yang dilakukan para dalang seperti Ki H. Asep Sunandar Sunarya. Wayang memiliki gaya tutur dan keunikan tersendiri yang membuatnya diakui sebagai warisan budaya oleh UNESCO pada tahun 2003.",
        sample_question = listOf(
            "Apa itu wayang?",
            "Wayang berasal dari?",
            "Di mana pertama kali wayang ditemukan?"
        )
    ),
    ContextData(
        id = 3,
        title = "Bahasa Jawa",
        context = "Bahasa Jawa adalah bahasa Austronesia yang utamanya dituturkan oleh penduduk Jawa di bagian tengah dan timur pulau Jawa, serta oleh diaspora Jawa di Indonesia dan luar negeri. Bahasa Jawa memiliki sekitar 80 juta penutur pada tahun 2023. Sejarah tulisan bahasa Jawa dimulai sejak abad ke-9 dengan bahasa Jawa Kuno yang berevolusi menjadi bahasa Jawa Baru pada abad ke-15. Bahasa Jawa awalnya menggunakan aksara dari India yang diadaptasi menjadi aksara Jawa, meskipun kini lebih sering ditulis dengan alfabet Latin. Bahasa Jawa memiliki tradisi sastra tertua kedua di antara bahasa-bahasa Austronesia setelah bahasa Melayu. Bahasa ini juga memiliki sistem tingkat tutur yang kompleks yang penggunaannya ditentukan oleh status sosial antara pembicara dan lawan bicara.",
        sample_question = listOf(
            "Siapa yang biasanya menggunakan bahasa Jawa?",
            "Dari mana asal mula tercipta bahasa Jawa?",
            "Kapan pertama kali munculnya bahasa Jawa Baru?"
        )
    ),
    ContextData(
        id = 4,
        title = "Tembang Jawa",
        context = "Sejak zaman dahulu, suku Jawa telah menciptakan banyak karya lagu yang disebut tembang atau sekar, yang memiliki fungsi dan ciri khas masing-masing. Tembang merupakan bentuk kebudayaan yang berkembang di Jawa dari zaman kerajaan kuno hingga saat ini dan memiliki tujuan serta filosofi tersendiri, dengan aturan tertentu seperti jumlah suku kata dan rima. Ada tiga jenis tembang utama dalam budaya Jawa: Tembang Gedhe, yang merupakan tembang klasik digunakan dalam pembuka gendhing dan pertunjukan wayang, dengan contoh seperti Candrakusuma dan Kusumastuti, disebut juga sebagai tembang klasik atau sekar ageng; Tembang Tengahan, yang menggunakan bahasa lebih modern dibanding tembang gedhe tetapi masih terikat aturan, dengan contoh seperti Balabak dan Girisa, disebut juga sebagai sekar madya; serta Tembang Macapat, yang paling modern dan tidak memerlukan gamelan untuk pengiring, dengan contoh seperti Kinanthi dan Megatruh, disebut juga sebagai sekar alit, eksistensinya muncul di zaman akhir Kerajaan Mataram. Masing-masing jenis tembang ini bukan hanya sekedar nyanyian, tetapi memiliki filosofi yang dalam dan aturan yang ketat, mencerminkan kehidupan masyarakat Jawa dari masa ke masa.",
        sample_question = listOf(
            "Suku Jawa membuat karya lagu yang disebut sebagai?",
            "Tembang Gedhe sering ditemukan di acara apa?",
            "Apa nama lain Tembang Macapat?"
        )
    ),
    ContextData(
        id = 5,
        title = "Dongeng Timun Mas",
        context = "Pada zaman dahulu, di sebuah desa dekat hutan, hiduplah sepasang petani yang belum memiliki anak, setiap hari mereka berdoa kepada Tuhan agar diberi anak. Suatu hari, doa mereka didengar oleh seorang raksasa. Sang raksasa menawarkan bantuan kepada sepasang petani itu dan berkata bahwa mereka akan memiliki anak dari buah timun yang mereka tanam di ladang mereka, tetapi sang raksasa mengajukan syarat bahwa mereka harus menyerahkan anak mereka kepada sang raksasa ketika anaknya berusia 17 tahun. Tanpa pikir panjang, sepasang petani itu menyetujui syarat tersebut. Anak dari sepasang petani itu lahir dan diberi nama Timun Mas. Mereka hidup bahagia sampai ketika Timun Mas berumur 17 tahun, sang raksasa datang menagih janjinya, jadi sepasang petani itu ketakutan dan melupakan janji mereka. Mereka menyuruh Timun Mas melarikan diri dengan membawa empat benda ajaib: biji timun, jarum, garam, dan terasi. Ketika dikejar sang raksasa, Timun Mas menyebar biji timun yang berubah menjadi ladang timun. Sang raksasa berhenti untuk memakan timun, tetapi setelah kenyang, ia kembali mengejar Timun Mas. Kemudian, Timun Mas menyebar jarum yang berubah menjadi hutan bambu berduri. Ketika sang raksasa hampir menangkapnya lagi, Timun Mas menyebar garam yang berubah menjadi lautan. Sang raksasa berenang dan terus mengejar hingga akhirnya Timun Mas menyebar terasi yang berubah menjadi lumpur. Pada akhirnya sang raksasa tenggelam ke dalam lumpur karena kelelahan.",
        sample_question = listOf(
            "Apa doa kedua petani kepada Tuhan?",
            "Syarat apa yang diberikan oleh sang raksasa kepada petani?",
            "Apa yang terjadi pada sang raksasa di akhir cerita?"
        )
    ),
    ContextData(
        id = 6,
        title = "Permainan Congklak",
        context = "Congklak adalah permainan tradisional dari Jawa Tengah. Permainan dilakukan oleh dua orang menggunakan papan congklak dan biji congklak. Biji yang dibutuhkan dalam permainan ini berjumlah 49 butir per orang. Papan congklak khas dari Jawa Tengah biasanya berbahan dasar kayu. Total lubang di atas papan adalah 16. Dengan 7 lubang di masing-masing sisi dan saling berhadapan, pemain diharuskan 'menembak' biji lawan yang masih ada dalam lubang papan. Di awal permainan, masing-masing lubang terisi 7 biji congklak. Setelah suwit, pemain bisa langsung mengambil biji dari lubang sendiri dan meletakkannya satu per satu hingga biji dalam genggaman habis. Permainan dianggap selesai jika sudah tidak ada lagi biji di atas papan yang bisa diambil dan dimainkan. Pemenang permainan tradisional Jawa Tengah ini adalah orang yang mendapatkan biji paling banyak.",
        sample_question = listOf(
            "Apa yang digunakan dalam bermain congklak?",
            "Berapa jumlah biji di awal permainan?",
            "Bagaimana kriteria pemenang dalam permainan congklak?"
        )
    ),
    ContextData(
        id = 7,
        title = "Elang Jawa yang Terancam Punah",
        context = "Elang Jawa dengan nama latin Nisaetus bartelsi adalah burung pemangsa berukuran sedang hingga besar yang endemik di Pulau Jawa, termasuk dalam keluarga Accipitridae dan genus Nisaetus. Dengan panjang tubuh antara 60-70 cm, elang ini memiliki ciri khas seperti kepala cokelat kemerahan dengan jambul tinggi, tengkuk cokelat kekuningan, serta bulu punggung dan sayap yang cokelat gelap. Suara elang ini nyaring dan bervariasi, dengan bunyi khas 'klii-iiw' dan 'kli-kli-kli'. Elang Jawa tersebar di wilayah hutan primer dan pegunungan di Jawa, dari Taman Nasional Ujung Kulon di barat hingga Semenanjung Blambangan di timur, serta dapat ditemukan pada ketinggian hingga 3.000 mdpl. Makanan utamanya meliputi reptil, burung kecil, mamalia kecil, dan bahkan anak monyet. Sarangnya berupa tumpukan ranting di pohon tinggi, dengan satu telur yang dierami selama sekitar 47 hari. Populasi elang Jawa diperkirakan antara 600-1.000 ekor, dengan ancaman besar dari kehilangan habitat dan perburuan ilegal. Karena kelangkaannya, elang ini dianggap terancam kepunahan (EN) oleh IUCN dan dilindungi oleh undang-undang Indonesia.",
        sample_question = listOf(
            "Berapa panjang elang Jawa?",
            "Apa ciri khas elang Jawa?",
            "Seberapa banyak jumlah elang Jawa sekarang?"
        )
    )
)

