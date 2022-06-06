import { Pipe, PipeTransform } from '@angular/core';
@Pipe({ name: 'fahrenheitPipe' })
export class FahrenheitPipe implements PipeTransform {
transform(celsius: number): string {
let fah = celsius * 9 / 5 + 32;
let output = fah.toString() + " " + '\u2109'; // Unicode for Fahrenheit;
return output;
}
}