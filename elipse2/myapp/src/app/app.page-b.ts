import { Component } from '@angular/core';
@Component({
template: `This is page b. {{retrievedFromStorage}}
<input type="input" (ngModelChange)="mychange($event)" [ngModel]="mymodel">
<input type="input" (ngModelChange)="mychange2($event)" [ngModel]="mymodel2">
`
})
export class PageBComponent {
    retrievedFromStorage : string | null = sessionStorage.getItem('your_key');
    mymodel! : string;
    mymodel2! : string;
constructor() {
}
mychange(val:string) {
sessionStorage.setItem("autosave", val);
console.log(val); // updated value
}
mychange2(val:string) {
    sessionStorage.setItem("autosave", val);
    console.log(val); // updated value
    }
}