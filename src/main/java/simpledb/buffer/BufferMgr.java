package simpledb.buffer;

import simpledb.file.*;
import simpledb.log.LogMgr;
import java.util.*;
/**
 * Manages the pinning and unpinning of buffers to blocks.
 * @author Edward Sciore
 *
 */
public class BufferMgr {
   // private Buffer[] bufferpool; /* buffer pool */ 

   private List<Buffer> unpinnedBuffers; // List of unpinned buffers
   private Map<BlockId, Buffer> allocatedBuffers; // Map of allocated buffers, keyed on block

   // private int numAvailable;   /* the number of available (unpinned) buffer slots */
   private static final long MAX_TIME = 10000; /* 10 seconds */
   
   /**
    * Constructor:  Creates a buffer manager having the specified 
    * number of buffer slots.
    * This constructor depends on a {@link FileMgr} and
    * {@link simpledb.log.LogMgr LogMgr} object.
    * @param numbuffs the number of buffer slots to allocate
    */
   public BufferMgr(FileMgr fm, LogMgr lm, int numbuffs) {
      // bufferpool = new Buffer[numbuffs];
      // numAvailable = numbuffs;
      unpinnedBuffers = new LinkedList<>();
      allocatedBuffers = new HashMap<>();

      for (int i=0; i<numbuffs; i++){
         Buffer buffer = new Buffer(fm, lm, i);
         unpinnedBuffers.add(buffer);
      }
   }
   
   /**
    * Returns the number of available (i.e. unpinned) buffers.
    * @return the number of available buffers
    */
   public synchronized int available() {
   // public  int available() {
      // return numAvailable;
      return unpinnedBuffers.size();
   }
   
   /**
    * Flushes the dirty buffers modified by the specified transaction.
    * @param txnum the transaction's id number
    */
   public synchronized void flushAll(int txnum) {
   // public  void flushAll(int txnum) {
      // for (Buffer buff : bufferpool)
      for (Buffer buff : allocatedBuffers.values())
         if (buff.modifyingTx() == txnum){
            allocatedBuffers.remove(buff.block(), buff);
            unpinnedBuffers.add(buff);
            buff.flush();
         }
   }
   
   /**
    * Unpins the specified data buffer. If its pin count
    * goes to zero, then notify any waiting threads.
    * @param buff the buffer to be unpinned
    */
   public synchronized void unpin(Buffer buff) {
   // public void unpin(Buffer buff) {
      buff.unpin();

      if (!buff.isPinned()) {
         // numAvailable++;
         allocatedBuffers.remove(buff.block(), buff);
         unpinnedBuffers.add(buff);
         notifyAll();
      }
   }
   
   /**
    * Pins a buffer to the specified block, potentially
    * waiting until a buffer becomes available.
    * If no buffer becomes available within a fixed 
    * time period, then a {@link BufferAbortException} is thrown.
    * @param blk a reference to a disk block
    * @return the buffer pinned to that block
    */
   // public synchronized Buffer pin(BlockId blk) {
   public synchronized Buffer pin(BlockId blk) throws BufferAbortException{
      try {
         long timestamp = System.currentTimeMillis();
         Buffer buff = tryToPin(blk);
         while (buff == null && !waitingTooLong(timestamp)) {
            wait(MAX_TIME);
            buff = tryToPin(blk);
         }
         if (buff == null)
            throw new BufferAbortException();
         return buff;
      }
      catch(InterruptedException e) {
         throw new BufferAbortException();
      }
   }  
   
   /**
    * Returns true if starttime is older than 10 seconds
    * @param starttime timestamp 
    * @return true if waited for more than 10 seconds
    */
   private boolean waitingTooLong(long starttime) {
      return System.currentTimeMillis() - starttime > MAX_TIME;
   }
   
   /**
    * Tries to pin a buffer to the specified block. 
    * If there is already a buffer assigned to that block
    * then that buffer is used;  
    * otherwise, an unpinned buffer from the pool is chosen.
    * Returns a null value if there are no available buffers.
    * @param blk a reference to a disk block
    * @return the pinned buffer
    */
   private Buffer tryToPin(BlockId blk) {
      // Buffer buff = findExistingBuffer(blk);

      Buffer buff = chooseUnpinnedBuffer();
      if (buff == null)
           return null;
      if (!buff.isPinned()){
         buff.assignToBlock(blk);
         allocatedBuffers.put(blk, buff);
         buff.pin();
      }
      return buff;
   }
   
   /**
    * Find and return a buffer assigned to the specified block. 
    * @param blk a reference to a disk block
    * @return the found buffer       
    */
   // private Buffer findExistingBuffer(BlockId blk) {
   //    for (Buffer buff : bufferpool) {
   //       BlockId b = buff.block();
   //       if (b != null && b.equals(blk))
   //          return buff;
   //    }
   //    return null;
   // }
   
   /**
    * Find and return an unpinned buffer     . 
    * @return the unpinned buffer       
    */
   private Buffer chooseUnpinnedBuffer() {
      // for (Buffer buff : bufferpool)
      for (Buffer buff : unpinnedBuffers)
         if (buff != null){
            unpinnedBuffers.remove(buff);
            return buff;
         }
      return null;
   }

   /**
    * Prints the current status of the buffer manager,
    * including the ID, block, and pinned status of each
    * buffer in the allocated map, and the IDs of each
    * buffer in the unpinned list.
    */
    public synchronized void printStatus() {
      System.out.println("Allocated Buffers:");
      // for (buff : bufferpool) {
      for (Map.Entry<BlockId, Buffer> entry : allocatedBuffers.entrySet()) {
         Buffer buff = entry.getValue();
         BlockId block = entry.getKey();
         System.out.print("Buffer " + buff.getId() + ": "+ block.toString());
         if (buff.isPinned())
            System.out.println(" pinned");
         else 
            System.out.println(" unpinned");
      }
      System.out.print("Unpinned Buffers in LRU order: ");
      for (Buffer buff : unpinnedBuffers) {
         System.out.print(buff.getId() + " ");
      }
      System.out.println();
   }
}
