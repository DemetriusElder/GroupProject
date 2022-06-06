import { Component } from '@angular/core';
// This is the service.
export class MyDataService {
    myname: string;
    constructor() {
    this.myname =;
    }
    getmyNames() {
    return this.myname;
    }
 }