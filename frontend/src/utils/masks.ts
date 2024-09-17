export function maskCreditCardNumber(number: string): string {
  const cleaned = number.replace(/\D/g, '');
  const masked = cleaned.replace(/(\d{4})(?=\d)/g, '$1 ');

  return masked.trim();
}

export function maskOnlyNumber(value: string){
  if(!value){
    return ""
  }

  return value.replace(/\D/g, '')
}

export function maskExpirationDate(input: string): string {
  const cleaned = input.replace(/\D/g, '');
  let month = cleaned.slice(0, 2);
  
  if (parseInt(month, 10) > 12) {
    month = '12';
  }

  const year = cleaned.slice(2, 4);

  return month + (year ? '/' + year : '');
}

export function maskMoney(value: string) {
  let formattedValue = value.replace(/\D/g, "");
  formattedValue = (Number(formattedValue) / 100).toFixed(2); 
  formattedValue = formattedValue.replace(".", ",");
  value = "R$ " + formattedValue.replace(/\B(?=(\d{3})+(?!\d))/g, ".");

  return value;
}

export function removeMoneyMask(formattedValue: string){
  let numericValue = formattedValue.replace(/[R$\\.\s]/g, "");
  numericValue = numericValue.replace(",", ".");

  return parseFloat(numericValue);
}

export function removeExpirationDateMask(value: string){
  const [month, year] = value.split('/');
  return `20${year}-${month}-01`;
}

export function getOnlyNumbers(value: string){
  return value.replace(/\D/g, '')
}
