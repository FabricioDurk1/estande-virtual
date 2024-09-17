export function formatCreditCardNumber(value: string){
  const masked = value.replace(/(\d{4})(\d{4})(\d{4})(\d{4})/, '$1 $2 $3 $4');
  return masked;
}

export function formatAmountToCurrency(amount: number){
  return amount.toLocaleString("pt-BR", {
    style: 'currency',
    currency: 'BRL'
  })
}

export function getFormattedExpirationDate(value: string){
  const [year, month] = value.split('-');
  const yearTwoLastDigits = year.slice(-2);
  return `${month.padStart(2, '0')}/${yearTwoLastDigits}`;
}
