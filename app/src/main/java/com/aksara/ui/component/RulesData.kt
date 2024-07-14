package com.aksara.ui.component

data class RulesData(
    val id: Int,
    val text: String
)

val rulesItems = listOf(
    RulesData(1,
            "Jika seorang siswa mendapat nilai “Sangat Tinggi” dalam Bahasa Indonesia, terdapat kemungkinan sebesar 45.36% bahwa siswa tersebut akan mendapat nilai “Tinggi” dalam IPA. Kombinasi ini terjadi pada 23.15% data. Kemungkinan mendapatkan nilai “Tinggi” dalam IPA meningkat sebesar 1.05 kali jika siswa mendapat nilai “Sangat Tinggi” dalam Bahasa Indonesia."),
    RulesData(2,
            "Jika seorang siswa mendapat nilai “Tinggi” dalam IPA, terdapat kemungkinan sebesar 53.65% bahwa siswa tersebut akan mendapat nilai “Sangat Tinggi” dalam Bahasa Indonesia. Kombinasi ini terjadi pada 23.15% data. Kemungkinan mendapatkan nilai “Sangat Tinggi” dalam Bahasa Indonesia meningkat sebesar 1.05 kali jika siswa mendapat nilai “Tinggi” dalam IPA."),
    RulesData(3,
            "Jika seorang siswa mendapat nilai “Sangat Tinggi” dalam Bahasa Indonesia, terdapat kemungkinan sebesar 49.48% bahwa siswa tersebut akan mendapat nilai “Tinggi” dalam IPS. Kombinasi ini terjadi pada 25.26% data. Kemungkinan mendapatkan nilai “Tinggi” dalam IPS meningkat sebesar 1.22 kali jika siswa mendapat nilai “Sangat Tinggi” dalam Bahasa Indonesia."),
    RulesData(4,
            "Jika seorang siswa mendapat nilai “Tinggi” dalam IPS, terdapat kemungkinan sebesar 62.33% bahwa siswa tersebut akan mendapat nilai “Sangat Tinggi” dalam Bahasa Indonesia. Kombinasi ini terjadi pada 25.26% data. Kemungkinan mendapatkan nilai “Sangat Tinggi” dalam Bahasa Indonesia meningkat sebesar 1.22 kali jika siswa mendapat nilai “Tinggi” dalam IPS."),
    RulesData(5,
            "Jika seorang siswa mendapat nilai “Tinggi” dalam Bahasa Jawa, terdapat kemungkinan sebesar 57.57% bahwa siswa tersebut akan mendapat nilai “Sangat Tinggi” dalam Bahasa Indonesia. Kombinasi ini terjadi pada 20% data. Kemungkinan mendapatkan nilai “Sangat Tinggi” dalam Bahasa Indonesia meningkat sebesar 1.12 kali jika siswa mendapat nilai “Tinggi” dalam Bahasa Jawa."),
    RulesData(6,
            "Jika seorang siswa mendapat nilai “Sangat Tinggi” dalam Bahasa Indonesia, terdapat kemungkinan sebesar 45.36% bahwa siswa tersebut akan mendapat nilai “Sangat Tinggi” dalam IPA. Kombinasi ini terjadi pada 23.15% data. Kemungkinan mendapatkan nilai “Sangat Tinggi” dalam IPA meningkat sebesar 1.30 kali jika siswa mendapat nilai “Sangat Tinggi” dalam Bahasa Indonesia."),
    RulesData(7,
            "Jika seorang siswa mendapat nilai “Sangat Tinggi” dalam IPA, terdapat kemungkinan sebesar 66.66% bahwa siswa tersebut akan mendapat nilai “Sangat Tinggi” dalam Bahasa Indonesia. Kombinasi ini terjadi pada 23.15% data. Kemungkinan mendapatkan nilai “Sangat Tinggi” dalam Bahasa Indonesia meningkat sebesar 1.30 kali jika siswa mendapat nilai “Sangat Tinggi” dalam IPA."),
    RulesData(8,
            "Jika seorang siswa mendapat nilai “Sangat Tinggi” dalam Bahasa Indonesia, terdapat kemungkinan sebesar 46.39% bahwa siswa tersebut akan mendapat nilai “Sangat Tinggi” dalam Bahasa Jawa. Kombinasi ini terjadi pada 23.68% data. Kemungkinan mendapatkan nilai “Sangat Tinggi” dalam Bahasa Jawa meningkat sebesar 1.46 kali jika siswa mendapat nilai “Sangat Tinggi” dalam Bahasa Indonesia."),
    RulesData(9,
            "Jika seorang siswa mendapat nilai “Sangat Tinggi” dalam Bahasa Jawa, terdapat kemungkinan sebesar 75% bahwa siswa tersebut akan mendapat nilai “Sangat Tinggi” dalam Bahasa Indonesia. Kombinasi ini terjadi pada 23.68% data. Kemungkinan mendapatkan nilai “Sangat Tinggi” dalam Bahasa Indonesia meningkat sebesar 1.46 kali jika siswa mendapat nilai “Sangat Tinggi” dalam Bahasa Jawa.")
)
