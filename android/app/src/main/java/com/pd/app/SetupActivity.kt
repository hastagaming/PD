// Inside SetupActivity.kt
private fun processSelectedIso(file: File) {
    val scanner = DistroScanner()
    val busyboxUrl = scanner.getCompatibleBusyBox(file)
    
    // System automatically chooses for the user
    val intent = Intent(this, BusyBoxActivity::class.java).apply {
        putExtra("BUSYBOX_URL", busyboxUrl)
        putExtra("ISO_PATH", file.absolutePath)
    }
    startActivity(intent)
}
