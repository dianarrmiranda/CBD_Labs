function findSequences() {
  let sequenceNumbers = [];
  db.phones.find().forEach(function (doc) {
    let number = doc.components.number.toString();
    if (isSequence(number)) {
      sequenceNumbers.push(number);
    }
  });

  return sequenceNumbers;
}

function isSequence(number) {

  for (i = 0; i < number.length -1; i++)
    if (parseInt(number[i+1]) != parseInt(number[i]) + 1)
      return false

  return true;
}
