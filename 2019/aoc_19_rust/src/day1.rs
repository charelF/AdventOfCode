use std::fs::File;
use std::io::{BufRead, BufReader};
use std::io::Result;

pub fn day1() -> Result<()> {
    let file = File::open("src/input/1")?;
    let reader = BufReader::new(file);

    for line in reader.lines() {
        if let Ok(content) = line {
            println!("{}", content);
        }
    }

    Ok(())
}