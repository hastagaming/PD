use std::process::Command;

pub fn launch_pd() {
    let rootfs = "/data/data/com.pd.app/files/rootfs";
    
    Command::new("./proot")
        .arg("-r").arg(rootfs)
        .arg("-0")
        .arg("-b").arg("/dev")
        // ... argumen lainnya ...
        .spawn()
        .expect("Gagal menjalankan distro!");
}
