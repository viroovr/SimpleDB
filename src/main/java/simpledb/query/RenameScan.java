package simpledb.query;

public class RenameScan implements Scan {
   private Scan s;
   // private Schema schema;
   private String oldfldname;
   private String newfldname;

   /**
    * Creates a new rename scan, using the specified scan
    * and field names.
    * 
    * @param s         the scan of the underlying query
    * @param fieldlist the list of field names
    */
   public RenameScan(Scan s, String oldfldname, String newfldname) {
      this.s = s;
      this.oldfldname = oldfldname;
      this.newfldname = newfldname;
   }

   // Scan methods

   public void beforeFirst() {
      s.beforeFirst();
   }

   public boolean next() {
      return s.next();
   }

   public void close() {
      s.close();
   }

   public Constant getVal(String fldname) {
      if (fldname.equals(newfldname))
         return s.getVal(oldfldname);
      return s.getVal(fldname);
   }

   public int getInt(String fldname) {
      if (fldname.equals(newfldname))
         return s.getInt(oldfldname);
      return s.getInt(fldname);
   }

   public String getString(String fldname) {
      if (fldname.equals(newfldname))
         return s.getString(oldfldname);
      return s.getString(fldname);
   }

   public boolean hasField(String fldname) {
      if (fldname.equals(newfldname))
         return true;
      return s.hasField(fldname);
   }
}
