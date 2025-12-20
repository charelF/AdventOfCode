use std::fs::File;
use std::io::{BufRead, BufReader};
use std::io::Result;

pub fn day1() -> Result<()> {
    let file = File::open("src/input/1").expect("Cant read file");
    let reader = BufReader::new(file);

    let mut p1 = 0;
    let mut p2 = 0;

    for line in reader.lines() {
        if let Ok(content) = line {
            let mass = content.parse::<i32>().expect("Cant parse number");
            let fuel = (mass / 3) - 2;
            p1 += fuel;

            let mut rec_fuel = mass;
            loop {
                rec_fuel = (rec_fuel / 3) - 2;
                if rec_fuel <= 0 {
                    break;
                }
                p2 += rec_fuel;
            }
        }
    }

    println!("p1: {}", p1);
    println!("p2: {}", p2);
    Ok(())
}