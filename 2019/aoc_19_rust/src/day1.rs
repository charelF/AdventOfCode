use std::fs::File;
use std::io::{BufRead, BufReader};
use std::io::Result;

pub fn day1() -> Result<()> {
    let file = File::open("src/input/1").expect("Cant read file");
    let reader = BufReader::new(file);

    let mut sum = 0;

    for line in reader.lines() {
        if let Ok(content) = line {
            let mass = content.parse::<i32>().expect("Cant parse number");
            let fuel = (mass / 3) - 2;
            sum += fuel;
        }
    }
    println!("{}", sum);

    Ok(())
}