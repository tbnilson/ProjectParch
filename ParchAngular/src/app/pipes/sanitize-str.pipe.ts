import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'sanitizeStr'
})
export class SanitizeStrPipe implements PipeTransform {

  //All this pipe does is run decodeURI on the given string, then replaces angle brackets
  //with safe characters.
  transform(value: string, args?: any): string {

    let outstr = decodeURI(value);
    // outstr = outstr.replace(/</g, "&lt;").replace(/>/g, "&gt;");

    return outstr;
  }

}
