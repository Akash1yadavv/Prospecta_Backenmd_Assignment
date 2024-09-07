package com.prospecta.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CSVProcessorService {
	
    public String processCSV(BufferedReader reader) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;
        Map<String, String> cellDefinitions = new HashMap<>();  
        Map<String, Integer> cellValues = new HashMap<>(); 

        int rowIndex = 1;  
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(","); 

            for (int colIndex = 0; colIndex < values.length; colIndex++) {
                String value = values[colIndex].trim();
                String cellName = getCellName(rowIndex, colIndex + 1);  // e.g., A1, B2, etc.
                cellDefinitions.put(cellName, value); 
            }
            rowIndex++;
        }

      
        for (Map.Entry<String, String> entry : cellDefinitions.entrySet()) {
            String cellName = entry.getKey();
            String cellValue = entry.getValue();

            
            if (cellValue.startsWith("=")) {
                int resultValue = evaluateFormula(cellValue.substring(1), cellDefinitions, cellValues);
                cellValues.put(cellName, resultValue);  // Store the resolved value
            } else {
                try {
                    int intValue = Integer.parseInt(cellValue);
                    cellValues.put(cellName, intValue);  // Store the value directly
                } catch (NumberFormatException e) {
                    cellValues.put(cellName, 0);
                }
            }
        }
        int totalRows = rowIndex - 1;
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= 3; j++) {  // Assuming 3 columns (A, B, C)
                String cellName = getCellName(i, j);
                result.append(cellValues.getOrDefault(cellName, 0));  // Default to 0 if unresolved
                if (j < 3) {
                    result.append(",");
                }
            }
            result.append("\n");
        }

        return result.toString();
    }

   
    private int evaluateFormula(String formula, Map<String, String> cellDefinitions, Map<String, Integer> cellValues) {
        String[] parts = formula.split("[+]");
        int total = 0;
        for (String part : parts) {
            part = part.trim();
            if (part.matches("\\d+")) {
                total += Integer.parseInt(part);  
                total += cellValues.get(part);  
            } else if (cellDefinitions.containsKey(part)) {
                
                String cellDefinition = cellDefinitions.get(part);
                if (cellDefinition.startsWith("=")) {
                    total += evaluateFormula(cellDefinition.substring(1), cellDefinitions, cellValues);
                } else {
                    try {
                        int value = Integer.parseInt(cellDefinition);
                        cellValues.put(part, value);  // Cache the resolved value
                        total += value;
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid formula or value for cell: " + part);
                    }
                }
            } else {
                throw new IllegalArgumentException("Cell reference not found: " + part);
            }
        }
        return total;
    }


    private String getCellName(int rowIndex, int colIndex) {
        char columnName = (char) ('A' + colIndex - 1);
        return columnName + String.valueOf(rowIndex);
    }
}
