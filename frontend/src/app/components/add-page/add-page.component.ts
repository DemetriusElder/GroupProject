import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-page',
  templateUrl: './add-page.component.html',
  styleUrls: ['./add-page.component.css'],
})
export class AddPageComponent {
  postForm = new FormGroup(
    {
      title: new FormControl('', [
        Validators.required,
        Validators.minLength(15),
      ]),
      imageUrl: new FormControl('', [Validators.required]),
      content: new FormControl('', [
        Validators.required,
        Validators.minLength(200),
      ]),
    },
    { updateOn: 'submit' }
  );

  onSubmit(): void {
    if (!this.postForm.valid) {
      this.postForm.markAllAsTouched();
    }
    console.log(this.postForm.value);
  }

  get title() {
    return this.postForm.get('title');
  }
  get imageUrl() {
    return this.postForm.get('imageUrl');
  }
  get content() {
    return this.postForm.get('content');
  }
}
