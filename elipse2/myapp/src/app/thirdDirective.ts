import { Component } from '@angular/core';
// Define second directive.
@Component({
// Selector uses lower case with hyphens.
selector: 'third-directive',
template: `<h3>hello from the third directive</h3>`,
})
export class ThirdDirective {
}