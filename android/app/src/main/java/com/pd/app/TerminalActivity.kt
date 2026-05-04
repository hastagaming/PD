fun startLinuxDistro() {
    val scriptPath = "${filesDir.absolutePath}/scripts/boot.sh"
    
    // Memberikan izin eksekusi
    File(scriptPath).setExecutable(true)

    // Menjalankan script menggunakan ProcessBuilder
    val process = ProcessBuilder("/system/bin/sh", scriptPath)
        .directory(filesDir)
        .redirectErrorStream(true)
        .start()
    
    // Hubungkan output process ini ke Terminal View Komandan
}

