use nix::pty::{openpty, Winsize};
use std::os::unix::io::RawFd;
use std::process::{Command, Stdio};
use std::os::unix::process::CommandExt;

pub struct TerminalInstance {
    pub master_fd: RawFd,
    pub pid: i32,
}

pub fn spawn_shell(command: &str, args: Vec<String>) -> TerminalInstance {
    let pty = openpty(None, None).expect("Failed to open PTY");
    
    unsafe {
        let child = Command::new(command)
            .args(args)
            .stdin(Stdio::from_raw_fd(pty.slave))
            .stdout(Stdio::from_raw_fd(pty.slave))
            .stderr(Stdio::from_raw_fd(pty.slave))
            .pre_exec(|| {
                // Create new session for the child process
                libc::setsid();
                Ok(())
            })
            .spawn()
            .expect("Failed to spawn shell process");

        TerminalInstance {
            master_fd: pty.master,
            pid: child.id() as i32,
        }
    }
}
