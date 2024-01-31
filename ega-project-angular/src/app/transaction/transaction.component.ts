import { Component } from '@angular/core';
import * as pdfMake from 'pdfmake/build/pdfmake';
import * as pdfFonts from 'pdfmake/build/vfs_fonts';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent {
  transactionType!: string;
  amount!: number;

  downloadPdf() {
    let docDefinition = {
      content: [
        { text: `Transaction Type: ${this.transactionType}`, fontSize: 14 },
        { text: `Amount: ${this.amount}`, fontSize: 14 }
      ],
      vfs: pdfFonts.pdfMake.vfs
    };

    pdfMake.createPdf(docDefinition).download('transaction.pdf');
  }
}